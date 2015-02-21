(ns ^:figwheel-always cljs-ant.core
    (:require))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(def refresh-rate 200) ; miliseconds
(def ant-size 10) ; px
(def world-width (-> (.-body js/document) (.-clientWidth)))
(def world-height (-> (.-body js/document) (.-clientHeight)))

(defn random-ant-direction []
  :up)

(defn generate-ant [max-x max-y]
  {:x (Math/round (rand max-x))
   :y (Math/round (rand max-y))
   :direction (random-ant-direction)})

(defn generate-world []
  {:paper (js/Raphael 0 0 world-width world-height)
   :live-cells []
   :ant (generate-ant world-width world-height)})

(defonce world (atom (generate-world)))

(defn draw-cells [paper live-cells]
  (doseq [cell live-cells]
    (. paper rect (:x cell) (:y cell) ant-size ant-size)))

(defn draw-world []
  (let [paper (:paper @world)
        live-cells (:live-cells @world)]
    (. paper clear)
    (draw-cells paper live-cells)))

(defn update-world []
  (swap! world assoc :live-cells [{:x 100 :y 100} {:x 200 :y 200}]))

(defn loop-world []
  (update-world)
  (draw-world))

(defonce create-world
  (do
    (. js/window (setInterval (fn [] (loop-world)) refresh-rate))))

