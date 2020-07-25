(ns fibonacci)

(def fibonacci-numbers
  ((fn fib [a b]
     (lazy-seq (cons a (fib b (+ a b)))))
   0 1))

(defn -main []
  (do
    (println "First 10 positive and even Fibonacci numbers")
    (println (take 10 (filter #(and (even? %) (pos-int? %)) fibonacci-numbers)))))
