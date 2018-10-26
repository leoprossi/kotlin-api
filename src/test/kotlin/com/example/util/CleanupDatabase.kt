package com.example.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Connection
import java.util.*
import javax.sql.DataSource

/*
    Here you can see a more java way of injecting dependencies.
 */
@Component
class CleanupDatabase {

    @Autowired
    private val dataSource: DataSource? = null

    fun clean() {
        dataSource!!.connection.use { connection ->
            connection.createStatement().use { stmt ->

                val tables = findAllTables(connection)
                val primaryKeys = findAllPrimaryKeys(connection)

                stmt.executeUpdate("SET REFERENTIAL_INTEGRITY FALSE")
                for (table in tables) {
                    stmt.executeUpdate("TRUNCATE TABLE $table")
                    if (primaryKeys.containsKey(table)) {
                        stmt.executeUpdate("ALTER TABLE " + table + " ALTER COLUMN " + primaryKeys[table] + " RESTART WITH 1")
                    }
                }
                stmt.executeUpdate("SET REFERENTIAL_INTEGRITY TRUE")
            }
        }
    }

    private fun findAllPrimaryKeys(connection: Connection): Map<String, String> {
        connection.createStatement().use { stmt ->
            stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.CONSTRAINTS").use { rs ->
                val pks = HashMap<String, String>()

                while (rs.next()) {
                    val constraintType = rs.getString("CONSTRAINT_TYPE")
                    if ("PRIMARY_KEY".equals(constraintType, ignoreCase = true)) {
                        pks[rs.getString("TABLE_NAME")] = rs.getString("COLUMN_LIST")
                    }
                }

                return pks
            }
        }
    }

    private fun findAllTables(connection: Connection): Set<String> {
        val tables = HashSet<String>()

        val metaData = connection.metaData
        metaData.getTables(null, null, "%", null).use { rs ->
            while (rs.next()) {
                if ("TABLE".equals(rs.getString(4), ignoreCase = true)) {
                    tables.add(rs.getString(3))
                }
            }
        }

        return tables
    }
}