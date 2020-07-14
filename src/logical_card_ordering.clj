(ns logical-card-ordering
    (:require [clojure.math.combinatorics :as combo]))

(with-open [rdr (clojure.java.io/reader "word-cards.txt")]
  (def cards (vec (line-seq rdr))))

(defn edit-distance
  "Compute the edit distance between two strings."
  ([str1 str2] (edit-distance str1 str2 (count str1) (count str2)))
  ([str1 str2 m n] (cond (= m 0) n
                         (= n 0) m
                         (= (nth str1 (- m 1))
                            (nth str2 (- n 1))) (recur str1 str2 (dec m) (dec n))
                         :else (+ 1
                                  (min (edit-distance str1 str2 m (dec n))
                                       (edit-distance str1 str2 (dec m) n)
                                       (edit-distance str1 str2 (dec m) (dec n)))))))

(defn make-pairs
  [first rest])

(def pairs (make-pairs (first cards) (rest cards)))


(defn -main []
  (println (edit-distance (nth cards 0) (nth cards 1))))
