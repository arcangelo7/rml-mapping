# OpenCitations Bibliographic Metadata RDF Mapping

This repository contains the necessary mappings and configurations to convert CSV tables containing bibliographic metadata into RDF format. The data in the CSV tables are formatted according to the specifications defined for processing by OpenCitations, with formatting rules detailed in [this document](http://ceur-ws.org/Vol-3220/invited-talk2.pdf). The transformation leverages the RML (RDF Mapping Language) framework to achieve this conversion.

## Contents of the Repository

- [`rules.yml`](https://github.com/arcangelo7/rml-mapping/blob/main/rules.yml): A file that defines the mappings using YARRRML, a human-readable text-based representation to express RML mappings.
- [`rules.rml.ttl`](https://github.com/arcangelo7/rml-mapping/blob/main/rules.rml.ttl): Contains the RML rules generated from `rules.yml`.
- [`functions.ttl`](https://github.com/arcangelo7/rml-mapping/blob/main/functions.ttl): Defines custom functions used within the RML mappings.
- [`metadata.csv`](https://github.com/arcangelo7/rml-mapping/blob/main/metadata.csv): An example CSV file with input data.
- [`rml-mapping/`](https://github.com/arcangelo7/rml-mapping/tree/main/rml-mapping): A Java project directory that contains the definition of custom functions.

## Custom Functions

The repository includes Java-based custom functions that are crucial for the mapping process. These are defined in the [`rml-mapping/`](https://github.com/arcangelo7/rml-mapping/tree/main/rml-mapping) project under [`rml-mapping/src/main/java/com/opencitations/CustomFunctions.java`](https://github.com/arcangelo7/rml-mapping/blob/main/rml-mapping/src/main/java/com/opencitations/CustomFunctions.java).

### Modifying Custom Functions

To modify custom functions, you should update both the [`functions.ttl`](https://github.com/arcangelo7/rml-mapping/blob/main/functions.ttl) file and [`rml-mapping/src/main/java/com/opencitations/CustomFunctions.java`](https://github.com/arcangelo7/rml-mapping/blob/main/rml-mapping/src/main/java/com/opencitations/CustomFunctions.java) accordingly. After making changes, navigate to the [`rml-mapping`](https://github.com/arcangelo7/rml-mapping/tree/main/rml-mapping) directory and run the following Maven command to compile and package the updates:

```bash
mvn clean compile package
```

This command generates an updated JAR file at [`rml-mapping/target/rml-mapping-1.0-SNAPSHOT-jar-with-dependencies.jar`](https://github.com/arcangelo7/rml-mapping/blob/main/rml-mapping/target/rml-mapping-1.0-SNAPSHOT-jar-with-dependencies.jar).

## Generating RML from YARRRML

To convert YARRRML mappings ([`rules.yml`](https://github.com/arcangelo7/rml-mapping/blob/main/rules.yml)) into RML mappings ([`rules.rml.ttl`](https://github.com/arcangelo7/rml-mapping/blob/main/rules.rml.ttl)), use the following command with the yarrrml-parser:

```bash
yarrrml-parser -i rules.yml -o rules.rml.ttl
```

The yarrrml-parser can be installed following the instructions available at [yarrrml-parser GitHub repository](https://github.com/RMLio/yarrrml-parser).

## Generating RDF from RML

To generate RDF from the RML mappings, use the RMLMapper tool with the following command:

```bash
java -jar rmlmapper.jar -m rules.rml.ttl -f functions.ttl
```

You can obtain the `rmlmapper.jar` from the [RMLMapper Java GitHub repository](https://github.com/RMLio/rmlmapper-java).

## Getting Started

1. Clone this repository.
2. Ensure you have Java and Maven installed on your system.
3. Follow the instructions above to modify custom functions if necessary and to generate the RDF data from your CSV input.

This setup is designed to facilitate the conversion of bibliographic metadata into RDF, enabling the integration of such data into the Semantic Web and other RDF-compatible environments.

