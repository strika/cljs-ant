(ns ^:figwheel-always cljs-ant.core
    (:require))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(def refresh-rate 10) ; miliseconds
(def world-width (-> (.-body js/document) (.-clientWidth)))
(def world-height (-> (.-body js/document) (.-clientHeight)))

(defn generate-ant [max-x max-y]
  {:x (Math/round (rand max-x))
   :y (Math/round (rand max-y))})

(defn generate-world []
  {:paper (js/Raphael 0 0 world-width world-height)
   :ant (generate-ant world-width world-height)})

(defonce world (atom (generate-world)))

(defn draw-world []
  (let [paper (:paper @world)]
    (. paper clear)
    (-> (. paper (circle 50 50 10))
        (. (attr "fill" "#00f")))))

(defn update-world [])

(defn loop-world []
  (update-world)
  (draw-world))

(defonce create-world
  (do
    (. js/window (setInterval (fn [] (loop-world)) refresh-rate))))

