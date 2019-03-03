package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final MealDao mealDao = MealsUtil.mealDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "" : req.getParameter("action");
        int userId = req.getParameter("id") == null ? 0 : Integer.parseInt(req.getParameter("id"));

        switch (action.toLowerCase()) {
            case "delete":
                log.debug("delete the meal");
                mealDao.remove(userId);
                resp.sendRedirect("meals");
                break;
            case "edit":
                log.debug("edit the meal");
                Meal meal = mealDao.getById(userId);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
                break;
            case "add":
                log.debug("add the meal");
                meal = new Meal(LocalDateTime.now(), "", 1000);
                req.setAttribute("meal", meal);
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
                break;

            default:
                log.debug("redirect to meals");
                List<MealTo> mealWithExcess = MealsUtil.getWithExcess(mealDao.getAll(), 2000);
                req.setAttribute("mealList", mealWithExcess);
                req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Meal meal = new Meal(
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));

        if (id == 0) {
            mealDao.add(meal);
        } else {
            meal.setId(id);
            mealDao.update(meal);
        }
        resp.sendRedirect("meals");
    }
}
