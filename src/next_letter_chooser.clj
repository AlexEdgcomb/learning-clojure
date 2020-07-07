(ns next-letter-chooser
    (:require [clojure.string :as str])
    (:require [clojure.set :as cs]))

(def all-letters (set (map str (map char (range 65 91)))))

(with-open [rdr (clojure.java.io/reader "already-letters.txt")]
  (def already-letters (set (line-seq rdr))))

(with-open [rdr (clojure.java.io/reader "all-words.txt")]
  (def all-words (set (line-seq rdr))))

(def not-yet-letters (cs/difference all-letters already-letters))

(defn word-uses-all-letters? [word letters]
  (every? letters (str/split word #"")))

(defn all-words-using-letters [letters]
  (set (filter #(word-uses-all-letters? % letters) all-words)))

(def already-words (all-words-using-letters already-letters))

(def not-yet-words (cs/difference all-words already-words))

(defn new-words-by-letters [letters]
  (cs/difference (all-words-using-letters letters) already-words))

(defn divider []
  (println "-------------------"))

(defn new-letter-stats [letter]
  (let [new-words (new-words-by-letters (conj already-letters letter))]
    (when (> (count new-words) 0)
      (print letter "would add ")
      (println (count new-words) "words:" (sort new-words))
      (divider))))

(defn -main []
  (println "Have" (count already-letters) "letters. To go:" (sort not-yet-letters))
  (println "Have" (count already-words) "of" (count all-words) "words. To go:" (count not-yet-words))
  (divider)
  (doseq [letter (sort not-yet-letters)] (new-letter-stats letter)))
