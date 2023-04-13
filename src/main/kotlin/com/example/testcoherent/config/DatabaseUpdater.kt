package com.example.testcoherent.config

import liquibase.integration.spring.SpringLiquibase
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component("liquibase")
class DatabaseUpdater(dataSource: DataSource) : SpringLiquibase() {
    init {
        super.dataSource = dataSource
        super.changeLog = "classpath:liquibase/db.changelog-master.xml"
    }
}