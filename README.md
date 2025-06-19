# Mandarin Learning Backend

This Spring Boot project provides a backend for a Mandarin learning application. Users post their English and pinyin responses to `/interaction`. The service stores the data as YAML, triggers OpenAI GPT-4o for analysis, and commits the results to a Git repository.

## Features

- REST endpoint `POST /interaction` accepting JSON with `english` and `pinyin` fields.
- Saves each interaction under `interactions/YYYY-MM-DD/` using indexed YAML files.
- Calls OpenAI GPT-4o to analyze the interaction and stores the reply as `*_response.yaml`.
- Updates progress files and commits changes using GitHub credentials.
- Modular services for GPT, YAML file handling, and Git operations.

## Configuration

Settings are defined in `src/main/resources/application.properties`:

```
openai.api.key=<your-openai-key>
interaction.base-dir=interactions
git.repo.path=<path-to-repo>
git.branch=main
```

## Building

Use Maven to build and test:

```bash
mvn clean package
```

## Running

```bash
mvn spring-boot:run
```

## API Example

```bash
curl -X POST http://localhost:8080/interaction \
     -H 'Content-Type: application/json' \
     -d '{"english":"hello","pinyin":"ni hao"}'
```

## License

This project is licensed under the Creative Commons Zero v1.0 Universal License.
