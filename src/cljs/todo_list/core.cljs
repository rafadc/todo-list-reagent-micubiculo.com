(ns todo-list.core
  (:require [todo-list.history :as history]
            [todo-list.views.about :as about]
            [todo-list.views.main :as main]
            [reagent.core :as reagent :refer [atom]]
            [reagent.session :as session]
            [secretary.core :as secretary :include-macros true]
            [cljsjs.react :as react]))

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'main/todo-list))

(secretary/defroute "/about" []
  (session/put! :current-page #'about/about-page))

;; -------------------------
;; Initialize app
(defn current-page []
  [:div [(session/get :current-page)]])

(defn init! []
  (history/hook-browser-navigation!)
  (reagent/render-component [current-page] (.getElementById js/document "app")))
