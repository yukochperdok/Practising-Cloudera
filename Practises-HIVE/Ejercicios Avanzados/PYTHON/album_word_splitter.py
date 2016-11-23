import sys
import re

DELIMITER = '\t'
SPLITTER = re.compile('\s+')

for line in sys.stdin:
    line = line.rstrip('\n')
    album_start = line.rfind(DELIMITER)
    album = line if album_start < 0 else line[album_start + 1:]
    if album:
        for word in SPLITTER.split(album):
            print DELIMITER.join((line, word))

sys.exit(0)
