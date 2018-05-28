#!/bin/bash
RESULT_PATH=$2/app/release_notes.txt
COUNT=$(echo $1 | tr -s "." "\n" | wc -l)
echo "Start generating release notes"
git log -n $COUNT --pretty=format:%s > $RESULT_PATH