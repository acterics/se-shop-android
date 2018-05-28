#!/bin/bash

RESULT_PATH=$TRAVIS_BUILD_DIR/app/release_notes.txt
COUNT=$(echo $1 | tr -s "." "\n" | wc -l)
echo "Start generating release notes"
git log -n $1 --pretty=format:%s $COUNT> $RESULT_PATH