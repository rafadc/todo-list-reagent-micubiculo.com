(ns todo-list.core
  (:require [todo-list.history :as history]
            [todo-list.views.about :as about]
            [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [cljsjs.react :as react]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to todo-list"]
   [:div [:a {:href "#/about"} "go to about page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about/about-page))

;; -------------------------
;; Initialize app
(defn init! []
  (history/hook-browser-navigation!)
  (reagent/render-component [current-page] (.getElementById js/document "app")))
