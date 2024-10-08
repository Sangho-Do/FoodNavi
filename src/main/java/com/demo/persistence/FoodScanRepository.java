package com.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.domain.Food;

public interface FoodScanRepository extends JpaRepository<Food, Integer> {
	
	public Food findFirstByOrderByFseqDesc();
	
	public Food findByName(String name);

	@Query("SELECT COUNT(food) FROM Food food ")
	public int getTotalFoodCount();
	
	@Query(value="SELECT r1.* FROM "
			+ "(SELECT DISTINCT Food.* FROM Food, Food_Detail, Food_Ingredient, Ingredient "
			+ "WHERE Food.fseq = Food_Detail.fseq "
			+ "AND Food.fseq = Food_Ingredient.fseq "
			+ "AND Food_Ingredient.iseq = Ingredient.iseq "
			+ "AND Food.useyn = 'y' "
			+ "AND Food.name LIKE %:searchName% "
			+ "AND Food.name NOT LIKE %:banName% "
			+ "AND Ingredient.name LIKE %:searchIngredient% "
			+ "AND Food_Detail.food_type LIKE %:foodType% "
			+ "AND Food_Detail.kcal >= :kcalMin "
			+ "AND Food_Detail.kcal <= :kcalMax "
			+ "AND Food_Detail.carb >= :carbMin "
			+ "AND Food_Detail.carb <= :carbMax "
			+ "AND Food_Detail.prt >= :prtMin "
			+ "AND Food_Detail.prt <= :prtMax "
			+ "AND Food_Detail.fat >= :fatMin "
			+ "AND Food_Detail.fat <= :fatMax "
			+ "AND (Food_Detail.carb*4 / Food_Detail.kcal) >= :ratioCarbMin "
			+ "AND (Food_Detail.carb*4 / Food_Detail.kcal) <= :ratioCarbMax "
			+ "AND (Food_Detail.prt*4 / Food_Detail.kcal) >= :ratioPrtMin "
			+ "AND (Food_Detail.prt*4 / Food_Detail.kcal) <= :ratioPrtMax "
			+ "AND (Food_Detail.fat*9 / Food_Detail.kcal) >= :ratioFatMin "
			+ "AND (Food_Detail.fat*9 / Food_Detail.kcal) <= :ratioFatMax "
			+ "MINUS "
			+ "SELECT DISTINCT Food.* FROM Food, Food_Ingredient, Ingredient "
			+ "WHERE Food.fseq = Food_Ingredient.fseq "
			+ "AND Food_Ingredient.iseq = Ingredient.iseq "
			+ "AND Ingredient.iseq IN (SELECT Ingredient.iseq FROM Ingredient "
											+ " WHERE egg = :no_egg "
											+ " OR milk = :no_milk "
											+ " OR bean = :no_bean "
											+ " OR shellfish = :no_shellfish "
											+ " OR name LIKE %:banIngredient% "
											+ " OR name LIKE %:allergyEtc% "
											+ " OR vegan_value < :vegetarian)) r1 "
			+ "ORDER BY r1.name ",
			nativeQuery=true)
	public List<Food> getFoodScanList(
			@Param("searchName") String searchName, @Param("searchIngredient") String searchIngredient, 
			@Param("banName") String banName, @Param("banIngredient") String banIngredient, 
			@Param("kcalMin") float kcalMin, @Param("kcalMax") float kcalMax, 
			@Param("carbMin") float carbMin, @Param("carbMax") float carbMax, 
			@Param("prtMin") float prtMin, @Param("prtMax") float prtMax, 
			@Param("fatMin") float fatMin, @Param("fatMax") float fatMax,	
			@Param("ratioCarbMin") float ratioCarbMin, @Param("ratioCarbMax") float ratioCarbMax, 
			@Param("ratioPrtMin") float ratioPrtMin, @Param("ratioPrtMax") float ratioPrtMax, 
			@Param("ratioFatMin") float ratioFatMin, @Param("ratioFatMax") float ratioFatMax, 
			@Param("no_egg") String no_egg, @Param("no_milk") String no_milk, 
			@Param("no_bean") String no_bean, @Param("no_shellfish") String no_shellfish,
			@Param("allergyEtc") String allergyEtc, @Param("vegetarian") int vegetarian, 
			@Param("foodType") String foodType);
	
	
	@Query(value="SELECT r1.* FROM "
			+ "(SELECT DISTINCT Food.* FROM Food, Food_Detail, Food_Ingredient, Ingredient "
			+ "WHERE Food.fseq = Food_Detail.fseq "
			+ "AND Food.fseq = Food_Ingredient.fseq "
			+ "AND Food_Ingredient.iseq = Ingredient.iseq "
			+ "AND Food.useyn = 'y' "
			+ "AND Food.name LIKE %:searchName% "
			+ "AND Food.name NOT LIKE %:banName% "
			+ "AND Ingredient.name LIKE %:searchIngredient% "
			+ "AND Food_Detail.food_type LIKE %:foodType% "
			+ "AND Food_Detail.kcal >= :kcalMin "
			+ "AND Food_Detail.kcal <= :kcalMax "
			+ "AND Food_Detail.carb >= :carbMin "
			+ "AND Food_Detail.carb <= :carbMax "
			+ "AND Food_Detail.prt >= :prtMin "
			+ "AND Food_Detail.prt <= :prtMax "
			+ "AND Food_Detail.fat >= :fatMin "
			+ "AND Food_Detail.fat <= :fatMax "
			+ "AND (Food_Detail.carb*4 / Food_Detail.kcal) >= :ratioCarbMin "
			+ "AND (Food_Detail.carb*4 / Food_Detail.kcal) <= :ratioCarbMax "
			+ "AND (Food_Detail.prt*4 / Food_Detail.kcal) >= :ratioPrtMin "
			+ "AND (Food_Detail.prt*4 / Food_Detail.kcal) <= :ratioPrtMax "
			+ "AND (Food_Detail.fat*9 / Food_Detail.kcal) >= :ratioFatMin "
			+ "AND (Food_Detail.fat*9 / Food_Detail.kcal) <= :ratioFatMax "
			+ "AND Food.fseq IN (SELECT Food.fseq FROM Food, History WHERE Food.fseq = History.fseq AND History.meal_type IN (:morning, :lunch, :dinner, :snack)) "
			+ "MINUS "
			+ "SELECT DISTINCT Food.* FROM Food, Food_Ingredient, Ingredient "
			+ "WHERE Food.fseq = Food_Ingredient.fseq "
			+ "AND Food_Ingredient.iseq = Ingredient.iseq "
			+ "AND Ingredient.iseq IN (SELECT Ingredient.iseq FROM Ingredient "
											+ " WHERE egg = :no_egg "
											+ " OR milk = :no_milk "
											+ " OR bean = :no_bean "
											+ " OR shellfish = :no_shellfish "
											+ " OR name LIKE %:banIngredient% "
											+ " OR name LIKE %:allergyEtc% "
											+ " OR vegan_value < :vegetarian)) r1 "
			+ "ORDER BY r1.name ",
			nativeQuery=true)
	public List<Food> getFoodRecommendList(
			@Param("searchName") String searchName, @Param("searchIngredient") String searchIngredient, 
			@Param("banName") String banName, @Param("banIngredient") String banIngredient, 
			@Param("morning") String morning, @Param("lunch") String lunch, 
			@Param("dinner") String dinner, @Param("snack") String snack, 
			@Param("kcalMin") float kcalMin, @Param("kcalMax") float kcalMax, 
			@Param("carbMin") float carbMin, @Param("carbMax") float carbMax, 
			@Param("prtMin") float prtMin, @Param("prtMax") float prtMax, 
			@Param("fatMin") float fatMin, @Param("fatMax") float fatMax,	
			@Param("ratioCarbMin") float ratioCarbMin, @Param("ratioCarbMax") float ratioCarbMax, 
			@Param("ratioPrtMin") float ratioPrtMin, @Param("ratioPrtMax") float ratioPrtMax, 
			@Param("ratioFatMin") float ratioFatMin, @Param("ratioFatMax") float ratioFatMax, 
			@Param("no_egg") String no_egg, @Param("no_milk") String no_milk, 
			@Param("no_bean") String no_bean, @Param("no_shellfish") String no_shellfish, 
			@Param("allergyEtc") String allergyEtc, @Param("vegetarian") int vegetarian, 
			@Param("foodType") String foodType);
	
	
	@Query(value=" "
			+ "SELECT COUNT(DISTINCT Food.fseq) FROM Food, History "
			+ "WHERE Food.fseq = History.fseq "
			+ "AND History.meal_Type IN (:morning, :lunch, :dinner, :snack) ", 
			nativeQuery=true)
	public int getFoodCountByMealTypeInHistory(
			@Param("morning") String morning, @Param("lunch") String lunch, 
			@Param("dinner") String dinner, @Param("snack") String snack);

	@Query(value="SELECT f FROM Food f WHERE f.useyn = 'y'")
	public List<Food> getAllByFood();
}
