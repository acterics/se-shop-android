#!/bin/bash

RESULT_PATH=./release_notes.txt

echo "Start generating release notes"
git log -n $1 --pretty=format:%s > $RESULT_PATH