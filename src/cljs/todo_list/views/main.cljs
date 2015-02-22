(ns todo-list.views.main)

(defn todo-list []
  [:div [:h2 "TODO list"]
   [:div [:a {:href "#/about"} "go to about page"]]])
