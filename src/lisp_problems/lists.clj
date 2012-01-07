(ns lisp-problems.lists)

(defn my-last [x]
    (first (reverse x)))

(defn my-last-2 [x]
    (if (= 1 (count x))
        (first x)
        (recur (rest x))))

(defn my-but-last [x]
    (if (= 2 (count x))
        (first x)
        (recur (rest x))))

(defn element-at [x n]
    (nth x n))

(defn element-at-2 [x n]
    (if (= n 0)
        (first x)
        (recur (rest x) (dec n))))

(defn number-of-elements [x]
    (count x))

(defn number-of-elements-2 [x]
    (loop [xs x total 0]
        (if (empty? xs)
            total
            (recur (rest xs) (inc total)))))

(defn number-of-elements-3 [x]
    (reduce (fn [l x] (inc l)) 0 x))

(defn my-reverse [lst]
    (reduce #(cons %2 %1) '() lst))

(defn palindrome [x]
    (= x (reverse x)))

; consumes stack...
(defn my-flatten [x]
    (reduce
        (fn [acc e]
            (if (list? e)
                (concat acc (my-flatten e))
                (concat acc (list e)))
        )
        '()
        x
    ))

(defn my-compress [lst]
    (reduce (fn [acc e]
        (cond
            (or (empty? acc) 
                (not (= e (last acc))))
                (concat acc (list e))
            :default acc
        )
    ) '() lst))

(defn my-compress-2 [lst]
    (map first (partition-by identity lst)))

(defn my-pack [lst]
    (partition-by identity lst))

(defn my-pack-2 [lst]
    (-> (reduce
            (fn [acc e]
                (let [vals (-> (get acc e)
                            count
                            inc)]
                    (assoc acc e (repeat vals e)))
            )
            (hash-map)
            lst
        )
        vals
        reverse))

(defn my-encode [lst]
    (->> (partition-by identity lst)
         (map #(list (count %) (first %)))))

(defn my-encode-2 [lst]
    (map #(list (count %) (first %))
        (my-pack lst)))

(defn my-encode-mod [lst]
    (map #(let [[n val] %] 
            (if (= 1 n) val %))
        (my-encode lst)))

(defn- explode-run-pair [[n x]]
    (repeat n x))

(defn- explode-run-element [x]
    (if (list? x)
        (explode-run-pair x)
        x))

(defn my-decode [lst]
    (flatten (map explode-run-element lst)))

(defn my-decode-2 [lst]
    (let [fuse #(if (list? %) (explode-run-pair %) (list %))
          explode-all (fn [acc e] (concat acc (fuse e)))]
        (reduce explode-all '() lst)))

(defn my-duplicate [lst]
    (reduce 
        #(concat %1 (repeat 2 %2))
        '() lst))

(defn my-duplicate-2 [lst]
    (mapcat #(list % %) lst))

(defn my-duplicate-x [lst x]
    (mapcat #(repeat x %) lst))

(defn my-drop-every-x [lst n]
    "Drop every nth item"
    (->> (partition-all n lst)
         (map (partial take (dec n)))
         (flatten)))

(defn my-split-first
    "Split list to first x items and rest"
    [lst x]
    (let [spl (partition-all x lst)]
        (conj '()
            (flatten (rest spl))
            (first spl))))

(defn my-split-first-2 [lst x]
    (list (take x lst) (drop x lst)))

(defn my-splice 
    "Splice a section of a list"
    [lst start end]
    (drop (dec start) (take end lst)))

(defn my-rotate [lst n]
    (loop [res lst c n]
        (if (= c 0)
            res
            (recur
                (cons (last res)
                      (take (dec (count res)) res))
                (dec c)))))

(defn my-rotate-2 [lst n]
    (let [total (count lst)
          rotate-by (mod (- total n) total)]
        (concat
            (drop rotate-by lst)
            (take rotate-by lst))))

(defn my-remove-at [lst n]
    (concat (take (dec n) lst)
            (drop n lst)))

(defn my-insert-at [x lst n]
    (let [[before after] (my-split-first-2 lst (dec n))]
        (concat before (list x) after)))

(defn my-range [start end]
    (loop [lst '() c end]
        (if (< c start)
            lst
            (recur (cons c lst) (dec c)))))

(defn my-random-elements
    "Extract a number of random elements fom a list"
    [lst n]
    (take n (shuffle lst)))

(defn my-random-numbers
    [n max]
    (my-random-elements (my-range 1 max) n))

(defn- my-rotations 
    "Generate all rotations of the given list"
    [lst]
    (reduce
        (fn [acc e]
            (cons (my-rotate lst (count acc)) acc)
        )
        '() lst))

(defn- my-weave 
    "Weave x into the list to generate permutations"
    [lst x]
    (let [size (+ 2 (count (first lst)))
          ranges (partial range 1 size)
          inserts (mapcat ranges (ranges))]
        (map
            #(my-insert-at x %1 %2)
            lst inserts
        )
    )
)

(defn my-permute 
    "Generates all n length permutations of list"
    [lst n]
    (-> (my-rotations (butlast lst))
        (my-duplicate-x (count lst))
        (my-weave (last lst))))

(multi-split-list '(a b c d e f g h i) '(2 3 4))

(defn multi-split-list
    "Split a list into sublists of specified lengths"
    [lst lengths]
    (loop [result '()
           remaining lst
           index (count lengths)]
        (if (= 0 index)
            result
            (recur
                (cons (take (nth lengths index) remaining))
                (drop (nth lengths index))
                (dec index))
        ))) 

(defn group-subsets
    "Group list items into all permutations of 2, 3 and 4 subsets"
    [lst])

