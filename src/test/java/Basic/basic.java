package Basic;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class basic {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://api.restful-api.dev";
//		given().log().all().header("content-type", "application/json;charset=utf-8").when().get("/objects").then().log()
//				.all().assertThat().statusCode(200);
//
//		given().log().all().header("content-type", "application/json;charset=utf-8").when().get("/objects/12").then()
//				.log().all().assertThat().statusCode(200).body("name", equalTo("Apple iPad Air"));

		String response = given().header("content-type", "application/json;charset=utf-8").when()
				.body(payload.addData()).post("/objects").then().assertThat().statusCode(200).extract().response()
				.asString();

		System.out.println("---------------------");
		System.out.println(response);

		JsonPath js = new JsonPath(response);
		String ID = js.get("id");
		System.out.println(ID);

		String name = js.getString("name");

		Boolean isPassed = false;

		try {
			Assert.assertEquals(name, "Apple MacBook Pro 16");

		} catch (Exception e) {
			System.out.println("This is failed");
		}

		finally {
			System.out.println("This is finally block");
		}

//		given().log().all().header("content-type", "application/json")
//				.body("{\n" + "  \"name\": \"Updating the new product\",\n" + "  \"data\": {\n"
//						+ "    \"year\": 2019,\n" + "    \"price\": 2049.99,\n"
//						+ "    \"CPU model\": \"Intel Core i9\",\n" + "    \"Hard disk size\": \"1 TB\",\n"
//						+ "    \"color\": \"silver\"\n" + "  }\n" + "}")
//				.when().put("/objects/12").then().log().all().assertThat().statusCode(200);

	}

}
