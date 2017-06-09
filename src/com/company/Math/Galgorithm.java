package com.company.Math;

import java.util.ArrayList;
import java.util.concurrent.atomic.DoubleAccumulator;

import com.company.MasterAlgoritm;
import com.company.Math.Constants;
import com.company.Initialization;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;


public class Galgorithm{

    // hardcode, later must be equals really countour count from frontend
//    public static int contourCount = 7;
    public static int contourCount = 50;

    public static ArrayList rectanglesData = new ArrayList();
//    public static String [][] cardCutting = new String[Constants.MAP][Constants.MAP];
    public static String [][] cardCutting = new String[30][30];
    public static ArrayList<String> transaction = new ArrayList<String>();
    public static boolean [][] structureMatrix = new boolean[contourCount+1][contourCount+1];
    public static String[] current_population;
    public static String[] gui_contours = new String[4];
    public static double[] gui_figure = new double[4];
    public static ArrayList<ArrayList<String>> tgBestList = new ArrayList<ArrayList<String>>();
    public static ArrayList<String> kpBestList = new ArrayList<>();
    public static String [][] all_statistics;
    public static String [][] fitness_statistics;
    public static ArrayList<Double> history_best_finder = new ArrayList<Double>();
    public static ArrayList<Double> final_best_finder = new ArrayList<Double>();
    public static Double winner_fitness;
    public static String winner_route;
    public static String winner_message = "";




    public static void initial(){


        System.out.println("INFO: initialization");

        // заполняем 0, для принятия решений по размещению в дальнейшем(Initialization.writeToCardCutting())
        for (int i = 0; i < Constants.MAP; i++) {
            for (int j = 0; j < Constants.MAP; j++) {
                cardCutting[i][j] = "0";
            }
        }

        Initialization.figureInit(Constants.FIGURE_FIRST);
        Initialization.figureInit(Constants.FIGURE_SECOND);
        Initialization.figureInit(Constants.FIGURE_THIRD);
//        Initialization.figureInit(Constants.FIGURE_FOURTH);
        Initialization.figureInit(Constants.FIGURE_FIVES);
        Initialization.figureInit(Constants.FIGURE_SIXTH);
//        Initialization.figureInit(Constants.FIGURE_SEVENTH);


        /** CONSOLE проверка корректности записанных данных фигур */
//        for (Object item : rectanglesData){
//        for(int i=0;i<rectanglesData.size();i++){
//            System.out.println("rectanglesData: "+rectanglesData.get(i));
//        }

        /** CONSOLE проверка корректности заполнения карты раскроя */
//        for (int i = 0; i < Constants.MAP; i++) {
//            // Цикл по второй размерности выводит колонки - вывод одной строки
//            for (int j = 0; j < Constants.MAP; j++) {
//                // Используем оператор print - без перехода на следующую строку
//                System.out.print(cardCutting[i][j]);
//            }
//            // Переход на следующую строку
//            System.out.println();
//        }

        /** CONSOLE проверка корректности заполнения структурной матрицы */
//        for (int i = 0; i <= contourCount; i++) {
//            // Цикл по второй размерности выводит колонки - вывод одной строки
//            for (int j = 0; j <= contourCount; j++) {
//                System.out.print(structureMatrix[i][j]);
//            }
//            System.out.println();
//        }

    }

    public static void gui_initial(String [] figures){


        System.out.println("INFO: gui_initialization");

        // заполняем 0, для принятия решений по размещению в дальнейшем(Initialization.writeToCardCutting())
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                cardCutting[i][j] = "0";
            }
        }

        for (String item : figures){
            gui_contours = item.split(",");
            for (int i = 0; i < gui_contours.length; i++){
                gui_figure[i] = Double.parseDouble(gui_contours[i]);
            }
            Initialization.figureInit(gui_figure);

        }

        /** CONSOLE проверка корректности записанных данных фигур */
//        for (Object item : rectanglesData){
//        for(int i=0;i<rectanglesData.size();i++){
//            System.out.println("rectanglesData: "+rectanglesData.get(i));
//        }

        /** CONSOLE проверка корректности заполнения карты раскроя */
//        for (int i = 0; i < Constants.MAP; i++) {
//            // Цикл по второй размерности выводит колонки - вывод одной строки
//            for (int j = 0; j < Constants.MAP; j++) {
//                // Используем оператор print - без перехода на следующую строку
//                System.out.print(cardCutting[i][j]);
//            }
//            // Переход на следующую строку
//            System.out.println();
//        }

        /** CONSOLE проверка корректности заполнения структурной матрицы */
//        for (int i = 0; i <= contourCount; i++) {
//            // Цикл по второй размерности выводит колонки - вывод одной строки
//            for (int j = 0; j <= contourCount; j++) {
//                System.out.print(structureMatrix[i][j]);
//            }
//            System.out.println();
//        }

    }

    public static void process(){
        /** старт выполнения*/
        System.out.println("INFO: start process");
        // TODO: размер популяции - rp нужно получить из frontend
        int target_rp = 20;
//        int target_kp = Constants.minKP;
        int target_kp = 1;
        int target_tg = Constants.minTG;
        double target_so = Constants.minSO;
        double target_ps = Constants.minPS;
        String data = "";

        // попытки шаг 4
        for (int kp = 0; kp < target_kp; kp++){
            // шаг 5. Формирование начальной популяции kp-ой попытки
            current_population = MasterAlgoritm.getInitialPopulation(target_rp);
            MasterAlgoritm.bindPopulationsFromDifferentKP(current_population, kp ,target_rp);
            // генерации шаг 6
            for (int tg = 1; tg <= target_tg; tg++){
                //выделение элиты
                current_population = MasterAlgoritm.sortElite(target_so, target_rp, current_population);

                //Кроссинговер
//                data = MasterAlgoritm.doCrossover(target_rp, target_ps, current_population);
            }
            System.out.println(data);
        }

        /** проверка заполненности общей бызы популяций */
//        for (int i = 0; i < target_kp; i++){
//            for (int j = 0; j < target_rp; j++){
//                System.out.print(MasterAlgoritm.common_population[i][j]);
//            }
//            System.out.println();
//        }
//
//        for (int i = 0; i < target_rp; i++){
//            System.out.print(new_population[i]);
//        }
    }

    public static void gui_process(String[] params){
        /** старт выполнения*/
        System.out.println("INFO: start gui_process");
        int target_rp = Integer.parseInt(params[0]);
        int target_tg = Integer.parseInt(params[1]);
        double target_ps = Double.parseDouble(params[2]);
        double target_pm = Double.parseDouble(params[3]);
        double target_so = Double.parseDouble(params[4]);
//        int target_kp = Integer.parseInt(params[5]);
        int target_kp = 2;

        all_statistics = new String [target_kp][target_tg];

        // попытки шаг 4
        for (int kp = 0; kp < target_kp; kp++){
            // шаг 5. Формирование начальной популяции kp-ой попытки
            current_population = MasterAlgoritm.getInitialPopulation(target_rp);
            MasterAlgoritm.bindPopulationsFromDifferentKP(current_population, kp ,target_rp);

            // генерации шаг 6
            for (int tg = 0; tg < target_tg; tg++){
                // Элитизм
                current_population = MasterAlgoritm.sortElite(target_so, target_rp, current_population);
                //Кроссинговер
                current_population = MasterAlgoritm.crossover(current_population.length, target_ps, current_population);
                // Мутации
                current_population = MasterAlgoritm.mutation(current_population.length,target_pm, current_population);
                // Селекция
                current_population = MasterAlgoritm.selection(target_rp, current_population);
                // Получение лучшего маршрута и значения фитнес-функции из обработанной популяции
//                tgBestList.add(MasterAlgoritm.getBestRouteFitness(current_population));
                all_statistics[kp][tg] = MasterAlgoritm.getBestRouteFitness(current_population);
                // отделяем значение функции для вычисления

                // Логирование лучшего результата текущей генерации
                System.out.println("KP: "+(kp+1)+" Generation #"+(tg+1)+" Best route - "+all_statistics[kp][tg]);

            }

        }

        // загрузка в список для передачи функции выбора лцчшего
        for (int i = 0; i < target_kp; i++){
            for (int j = 0; j < target_tg; j++){
                history_best_finder.add(Double.parseDouble(all_statistics[i][j].split("/")[1]));
                if (j==target_tg-1){
                    final_best_finder.add(MasterAlgoritm.fitnessSortBest(history_best_finder));
                    history_best_finder.clear();
                }
            }
        }

        winner_fitness = MasterAlgoritm.fitnessSortBest(final_best_finder);
        // достаем маршрут по значению фитнес функции
        for (int i = 0; i < target_kp; i++) {
            for (int j = 0; j < target_tg; j++) {
                if(winner_fitness == Double.parseDouble(all_statistics[i][j].split("/")[1])){
                    winner_route = all_statistics[i][j].split("/")[0];
                    break;
                }
            }
        }
        winner_message = "Лучшее значение целевой функции : "+winner_fitness+"  Лучший маршрут: "+ winner_route;


        for (int i = 0; i < target_kp; i++){
            for (int j = 0; j < target_tg; j++){
                System.out.print(all_statistics[i][j]+" | ");
            }
            System.out.println();
        }

        for (int i = 0; i < final_best_finder.size();i++){
            System.out.println("Best of KP#"+(i+1)+" = "+final_best_finder.get(i));
        }

        System.out.println("Лучшее значение целевой функции : "+winner_fitness+"  Лучший маршрут: "+winner_route);

        /** проверка заполненности общей бызы популяций */
//        for (int i = 0; i < target_kp; i++){
//            for (int j = 0; j < target_rp; j++){
//                System.out.print(MasterAlgoritm.common_population[i][j]);
//            }
//            System.out.println();
//        }
//
//        for (int i = 0; i < target_rp; i++){
//            System.out.print(new_population[i]);
//        }
    }
}
