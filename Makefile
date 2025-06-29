.PHONY = build, test
build:
	./gradlew clean build

test:
	./gradlew test testCodeCoverageReport
