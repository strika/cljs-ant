(ns ^:figwheel-always cljs-ant.ant
  (:require))

(defn- ant-position [world]
  {:x (get-in world [:ant :x])
   :y (get-in world [:ant :y])})

(defn- live-cell? [world]
  (some #(= (ant-position world) %) (:live-cells world)))

(defn- ant-direction [world]
  (get-in world [:ant :direction]))

(defn- turn-left [direction]
  (condp = direction
    :up :left
    :right :up
    :down :right
    :left :down))

(defn- turn-right [direction]
  (condp = direction
    :up :right
    :right :down
    :down :left
    :left :up))

(defn- calculate-ant-direction [world]
  (let [direction (ant-direction world)]
    (if (live-cell? world)
      (turn-left direction)
      (turn-right direction))))

(defn- turn-ant [world]
  (let [new-direction (calculate-ant-direction world)]
    (assoc-in world [:ant :direction] new-direction)))

(defn- kill-cell [world]
  (let [live-cells (:live-cells world)]
    (assoc world :live-cells (disj live-cells (ant-position world)))))

(defn- revive-cell [world]
  (let [live-cells (:live-cells world)]
    (assoc world :live-cells (conj live-cells (ant-position world)))))

(defn- flip-cell [world]
  (if (live-cell? world)
    (kill-cell world)
    (revive-cell world)))

(defn- move-ant-forward [world]
  (condp = (ant-direction world)
    :up    (update-in world [:ant :y] #(- % 1))
    :right (update-in world [:ant :x] #(+ % 1))
    :down  (update-in world [:ant :y] #(+ % 1))
    :left  (update-in world [:ant :x] #(- % 1))))

(defn move-ant [world]
  (let [ant (:ant world)
        live-cells (:live-cells world)]
    (-> world
        (turn-ant)
        (flip-cell)
        (move-ant-forward))))

