-- *********************************************************************
-- Update Database Script
-- *********************************************************************
-- Change Log: src/main/resources/config/liquibase/master.xml
-- Ran at: 1/21/17 2:30 AM
-- Against: SCHEDULING_PROJECT@jdbc:h2:file:./target/h2db/db/scheduling_project
-- Liquibase version: 3.4.2
-- *********************************************************************

-- Lock Database
UPDATE DATABASECHANGELOGLOCK SET LOCKED = TRUE, LOCKEDBY = '10.236.125.70 (10.236.125.70)', LOCKGRANTED = '2017-01-21 02:30:41.200' WHERE ID = 1 AND LOCKED = FALSE;

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-1::samuel (generated)
CREATE TABLE course_section (course_id BIGINT NOT NULL, section_id BIGINT NOT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-1', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 17, '7:df42e3dd4163e84cb471607a66cdc827', 'createTable', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-2::samuel (generated)
CREATE TABLE lecturer_course (course_id BIGINT NOT NULL, lecturer_id BIGINT NOT NULL);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-2', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 18, '7:b74ebe039bf1654a6240805178530bc9', 'createTable', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-3::samuel (generated)
ALTER TABLE course_section ADD PRIMARY KEY (section_id, course_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-3', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 19, '7:d1a230f0f00513ab853a5b588c1f49e7', 'addPrimaryKey', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-4::samuel (generated)
ALTER TABLE lecturer_course ADD PRIMARY KEY (lecturer_id, course_id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-4', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 20, '7:000e06aba4d5ec84d40cc5391180ef25', 'addPrimaryKey', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-5::samuel (generated)
ALTER TABLE course_section ADD CONSTRAINT FK_5ih3l7cf918xd98odtk5shrmw FOREIGN KEY (course_id) REFERENCES course (id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-5', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 21, '7:36589470288a9b96f7664e3851e5b686', 'addForeignKeyConstraint', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-6::samuel (generated)
ALTER TABLE lecturer_course ADD CONSTRAINT FK_abxn8m01hh44wcw0deq29p08w FOREIGN KEY (course_id) REFERENCES course (id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-6', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 22, '7:8086dc907f9338f0a918ddcd43836898', 'addForeignKeyConstraint', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-7::samuel (generated)
ALTER TABLE lecturer_course ADD CONSTRAINT FK_kjpaowcwh2sfaxx9h52a4s1g5 FOREIGN KEY (lecturer_id) REFERENCES lecturer (id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-7', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 23, '7:1e303578b0898a8b6dbccf9762613e4d', 'addForeignKeyConstraint', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-8::samuel (generated)
ALTER TABLE course_section ADD CONSTRAINT FK_ri1sn8pl2t6jy263qspsnro7n FOREIGN KEY (section_id) REFERENCES session (id);

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-8', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 24, '7:e082ada4cfc7e1ed8a4d0bed4e3c3ee8', 'addForeignKeyConstraint', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-9::samuel (generated)
ALTER TABLE LECTURER DROP CONSTRAINT FK_M4LNXDQ0H9JEBDT582VC1QV1N;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-9', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 25, '7:ac56b0369cbf9a39d767283ffe779fc4', 'dropForeignKeyConstraint', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-10::samuel (generated)
ALTER TABLE COURSE DROP CONSTRAINT FK_QJYFM5IDWAS5D0FD0AWWK6AAA;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-10', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 26, '7:62555f6f7bc4df9a40fefaff17cfd3a5', 'dropForeignKeyConstraint', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-11::samuel (generated)
ALTER TABLE COURSE DROP COLUMN LECTURER_ID;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-11', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 27, '7:c244c64e8a7749eebaadaecafb2b5ab4', 'dropColumn', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-12::samuel (generated)
ALTER TABLE LECTURER DROP COLUMN SESSION_ID;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-12', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 28, '7:bd6b77becfd256c2ad2349cba725bdb7', 'dropColumn', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Changeset config/liquibase/changelog/20170121072528_changelog.xml::1484983536026-13::samuel (generated)
ALTER TABLE jhi_user ALTER COLUMN  created_date SET DEFAULT NULL;

INSERT INTO DATABASECHANGELOG (ID, AUTHOR, FILENAME, DATEEXECUTED, ORDEREXECUTED, MD5SUM, DESCRIPTION, COMMENTS, EXECTYPE, CONTEXTS, LABELS, LIQUIBASE) VALUES ('1484983536026-13', 'samuel (generated)', 'config/liquibase/changelog/20170121072528_changelog.xml', NOW(), 29, '7:f69cd24ae652f66e3e46af33bcfee289', 'dropDefaultValue', '', 'EXECUTED', NULL, NULL, '3.4.2');

-- Release Database Lock
UPDATE DATABASECHANGELOGLOCK SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;

