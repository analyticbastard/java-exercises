(ns first-non-paired-integer)


(def in-the-middle [1 1 2 2 3 3 4 4 5 6 6 7 7 8 8 9 9])
(def to-the-left [1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9])
(def to-the-right [1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9])
(def skew-left [1 1 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9])

(def hr-testcase-0 [1])
(def hr-testcase-1 [1 1 2])
(def hr-testcase-2 [0 0 1 2 1])



(defn bin-search [myseq]
  (when-not (empty? myseq)
    (let [N (count myseq)
          n (Math/floor (double (/ N 2)))
          m (- N n 1)
          root (nth myseq n)
          i (first root)
          f (-> root second first)
          s (-> root second second)
          left (take n myseq)
          right (take-last m myseq)
          left (bin-search left)
          right (bin-search right)
          ]
      (if left
        left
        (if-not (= f s)
          {:index (* 2 i) :element f}
          right))
      )))


(defn lonely-integer [myseq]
  (->> myseq
       (#(conj % nil))
       (partition 2 )
       (#(interleave (range (count %)) %))
       (partition 2)
       bin-search
       ))


(lonely-integer in-the-middle)
(lonely-integer to-the-left)
(lonely-integer to-the-right)
(lonely-integer skew-left)
(lonely-integer hr-testcase-0)
(lonely-integer hr-testcase-1)
(lonely-integer hr-testcase-2)