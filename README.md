# Spring Boot log-parser


## Description
Repositry for parsing server logs and flag any long events that takes longer than 4ms.

## Getting Started

#### 1. Install Dependencies

if you are on Mac

```sh
# STEP 1: Install JDK 8
brew tap adoptopenjdk/openjdk
brew cask install adoptopenjdk8

# export java_home
export JAVA_HOME=$(/usr/libexec/java_home -v 1.8.0)

# Restart terminal or source bash profile

#  STEP 2: install gradle

brew install gradle

```

#### 2. Running on local

use vscode or other compiler to open the file. below script works on vscode.

```sh
# STEP 1: Clone Repo
git clone https://github.com/lanhoter/log-parser.git
cd log-parser
code .

# build executable jar 
./gradlew bootJar

# Run
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar serverLog.txt

# Test

./gradlew test

```



