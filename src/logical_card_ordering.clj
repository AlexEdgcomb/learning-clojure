(ns logical-card-ordering
    (:require [clojure.math.combinatorics :as combo])
    (:require [clojure.set :as cs]))

(with-open [rdr (clojure.java.io/reader "word-cards.txt")]
  (def cards (set (line-seq rdr))))

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

(def pairs (set (combo/combinations cards 2)))

(def cost-by-pair (apply merge (map #(hash-map % (edit-distance (first %) (second %))) pairs)))

(def cost-1-pairs (cs/select #(= (get cost-by-pair %) 1) pairs))

(def not-cost-1-pairs (cs/difference pairs cost-1-pairs))

(defn -main []
  (do
    (println cost-1-pairs)
    (println not-cost-1-pairs)))
