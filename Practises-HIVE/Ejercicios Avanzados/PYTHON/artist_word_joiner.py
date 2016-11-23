import sys
import itertools
import operator

DELIMITER = '\t'
key_value_pairs = (line.rstrip('\n').rsplit(DELIMITER, 1)
                   for line in sys.stdin)

for key, pairs in itertools.groupby(key_value_pairs, operator.itemgetter(0)):
    words = ' '.join(pair[1] for pair in pairs)
    print DELIMITER.join((key, words))

sys.exit(0)
