(ns genetic.contrib.regex.utils)

(defn split-regex [regex-string]
  (set (clojure.string/split regex-string #"\|")))

(defn join-regex [regex-collection]
  (clojure.string/join "|" regex-collection))

(defn count-true [collection]
  (count (filter identity collection)))

(defn count-false [collection]
  (count (filter #(not (identity %)) collection)))

(defn match? [regex-string string]
  (re-matches (re-pattern regex-string) string))

(defn count-match [regex-string collection]
  (count-true (map (partial match? regex-string) collection))
)

(defn count-no-match [regex-string collection]
  (count-false (map (partial match? regex-string) collection))
)
