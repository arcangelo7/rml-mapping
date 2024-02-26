# OpenCitations Bibliographic Metadata RDF Mapping

This repository contains the necessary mappings and configurations to convert CSV tables containing bibliographic metadata into RDF format. The data in the CSV tables are formatted according to the specifications defined for processing by OpenCitations, with formatting rules detailed in [this document](http://ceur-ws.org/Vol-3220/invited-talk2.pdf). The transformation leverages the RML (RDF Mapping Language) framework to achieve this conversion.

## Contents of the Repository

- `rules.yml`: A file that defines the mappings using YARRRML, a human-readable text-based representation to express RML mappings.
- `rules.rml.ttl`: Contains the RML rules generated from `rules.yml`.
- `functions.ttl`: Defines custom functions used within the RML mappings.
- `metadata.csv`: An example CSV file with input data.
- `rml-mapping/`: A Java project directory that contains the definition of custom functions.

## Custom Functions

The repository includes Java-based custom functions that are crucial for the mapping process. These are defined in the `rml-mapping` project under `src/main/java/com/opencitations/CustomFunctions.java`.

### Modifying Custom Functions

To modify custom functions, you should update both the `functions.ttl` file and `rml-mapping/src/main/java/com/opencitations/CustomFunctions.java` accordingly. After making changes, navigate to the `rml-mapping` directory and run the following Maven command to compile and package the updates:
