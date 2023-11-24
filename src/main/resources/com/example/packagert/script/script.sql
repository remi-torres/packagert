CREATE TABLE IF NOT EXISTS Project (
	project_id integer PRIMARY KEY AUTOINCREMENT,
	project_name text NOT NULL,
	project_path text NOT NULL
);

CREATE TABLE IF NOT EXISTS Feature (
	feature_id integer PRIMARY KEY AUTOINCREMENT,
	feature_name text NOT NULL,
	feature_parent_id integer,
	feature_project_id integer NOT NULL,
	FOREIGN KEY (feature_parent_id) REFERENCES Feature(feature_id),
	FOREIGN KEY (feature_project_id) REFERENCES Project(project_id)
);

CREATE TABLE IF NOT EXISTS Type (
	type_id integer PRIMARY KEY AUTOINCREMENT,
	type_name text NOT NULL,
	type_mariadbField text DEFAULT NULL,
	type_mariadbConstraint text DEFAULT NULL,
	type_sqliteField text DEFAULT NULL,
	type_sqliteConstraint text DEFAULT NULL,
	type_jpaPrefix text DEFAULT NULL,
	type_javaField text DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS DbColumn (
	column_id integer PRIMARY KEY AUTOINCREMENT,
	column_name text NOT NULL,
	column_feature_id integer NOT NULL,
	column_type text NOT NULL,
	FOREIGN KEY (column_feature_id) REFERENCES DbTable(feature_id)
);