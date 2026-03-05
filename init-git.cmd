@echo off
setlocal

echo =====================================
echo  Initializing Git Repository
echo =====================================

REM ---- Safety check ----
if not exist src (
  echo ERROR: src folder not found. Are you in project root?
  pause
  exit /b 1
)

REM ---- Remove old git if exists ----
if exist .git (
  echo Removing existing .git...
  rmdir /s /q .git
)

REM ---- README.md ----
echo Creating README.md...
type nul > README.md
echo # IVRCS >> README.md
echo. >> README.md
echo **Author:** Mr.Nattasit Modaram >> README.md
echo **Type:** Java Maven Project >> README.md
echo. >> README.md

echo # Git Guideline >> README.md
echo. >> README.md

echo ## Branch Strategy >> README.md
echo - main     : production-ready >> README.md
echo - develop  : integration branch >> README.md
echo - feature/*: new feature >> README.md
echo - hotfix/* : urgent fix >> README.md
echo. >> README.md

echo ## Commit Rules >> README.md
echo - One logical change per commit >> README.md
echo - Meaningful commit message >> README.md
echo - No build artifacts in repo >> README.md
echo. >> README.md

echo ## DO NOT COMMIT >> README.md
echo - target/ >> README.md
echo - logs >> README.md
echo - secrets >> README.md

REM ---- .gitignore ----
echo Creating .gitignore...
type nul > .gitignore

echo # ===== Java / Maven / Open Liberty ===== >> .gitignore
echo target/ >> .gitignore
echo *.class >> .gitignore
echo *.jar >> .gitignore
echo *.war >> .gitignore
echo *.ear >> .gitignore

echo. >> .gitignore
echo # ===== Gradle ===== >> .gitignore
echo build/ >> .gitignore
echo .gradle/ >> .gitignore

echo. >> .gitignore
echo # ===== IDEs ===== >> .gitignore
echo .classpath >> .gitignore
echo .project >> .gitignore
echo .settings/ >> .gitignore
echo .idea/ >> .gitignore
echo *.iml >> .gitignore
echo .vscode/ >> .gitignore

echo. >> .gitignore
echo # ===== CI / Jenkins ===== >> .gitignore
echo .jenkins/ >> .gitignore
echo *.pipeline >> .gitignore

echo. >> .gitignore
echo # ===== Logs / Temp ===== >> .gitignore
echo *.log >> .gitignore
echo *.tmp >> .gitignore

echo. >> .gitignore
echo # ===== OS ===== >> .gitignore
echo .DS_Store >> .gitignore
echo Thumbs.db >> .gitignore

echo. >> .gitignore
echo # ===== Secrets ===== >> .gitignore
echo .env >> .gitignore
echo *.key >> .gitignore
echo *.pem >> .gitignore
echo *.jks >> .gitignore

REM ---- .gitattributes ----
echo Creating .gitattributes...
type nul > .gitattributes
echo * text=auto >> .gitattributes

REM ---- .editorconfig ----
echo Creating .editorconfig...
type nul > .editorconfig
echo root=true >> .editorconfig
echo. >> .editorconfig
echo [*] >> .editorconfig
echo charset=utf-8 >> .editorconfig
echo end_of_line=lf >> .editorconfig
echo insert_final_newline=true >> .editorconfig
echo trim_trailing_whitespace=true >> .editorconfig

REM ---- Git init ----
git init
git branch -M main

REM ---- Add files safely ----
git add .gitignore .gitattributes .editorconfig README.md
REM ### if exist src git add src
if exist pom.xml git add pom.xml

git commit -m "Initial project structure"

REM ---- Create develop branch ----
REM git checkout -b develop
REM git commit --allow-empty -m "Create develop branch"
REM git checkout main

REM ---- Remote ----
git remote remove origin 2>nul
git remote add origin https://github.com/bsnky/ToyRobot.git
git push -u origin main

echo =====================================
echo  Git initialization completed
echo =====================================
pause
endlocal

REM git add .
REM git commit -m "Initial project"
REM git push -u origin main
