(ns ^:figwheel-always cljs-ant.world-view
  (:require))

(def ant-size 10) ; px
(def ant-color "#00f")
(def world-width (-> (.-body js/document) (.-clientWidth)))
(def world-height (.-innerHeight js/window))

(defn- generate-paper []
  (js/Raphael "paper" world-width world-height))

(defonce view (atom {:paper (generate-paper)}))

(defn- draw-cells [paper live-cells]
  (doseq [cell live-cells]
    (-> (. paper rect (:x cell) (:y cell) ant-size ant-size)
        (. attr "stroke" "#fff")
        (. attr "fill" ant-color))))

(defn draw-world [live-cells]
  (let [paper (:paper @view)]
    (. paper clear)
    (draw-cells paper live-cells)))

