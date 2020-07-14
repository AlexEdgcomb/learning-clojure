(ns fibonacci)

(def penultimate
  (comp second reverse))

(defn fib
  ([max v]
    (let [a (penultimate v) b (last v)]
      (if (> b max) v (fib max (conj v (+ a b))))))
  ([max] (fib max [0 1])))

(defn fib_str
  ([a b c & r]
    (str a " + " b " = " c "\n" (apply fib_str (conj r c b))))
  ([a b] ""))

(defn -main []
  (println (apply fib_str (fib 100))))
