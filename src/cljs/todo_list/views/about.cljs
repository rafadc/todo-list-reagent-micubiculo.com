(ns todo-list.views.about)

(defn about-page []
  [:div [:h2 "About todo-list"]
   [:div [:h3 "A demo app to evaluate the usage of Reagent"]]
   [:div
    [:p "Built by "
     [:a {:href "http://twitter.com/rafadc"} "@rafadc"]
     " for "
     [:a {:href "http://micubiculo.com"} "micubiculo"]]]
   [:div [:a {:href "#/"} "go to the home page"]]])
