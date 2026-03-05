@echo off
setlocal

echo =====================================
echo  Commit Git Repository
echo =====================================

git add .
git commit -m "Commit project"
git push -u origin main

echo =====================================
echo  Git commit completed
echo =====================================
pause
endlocal
