# Unit tests using Java and JUnit 5

## Objective

It is required to write a **delivery fee calculator** in **Java**. The delivery fee should be calculated based on several factors, including the distance to the destination, the size and fragility of the package, and the current load of the delivery service. The solution should be covered with unit tests using **JUnit 5**.

## Task requirements
- Write Java code for calculating the delivery fee
- Cover the solution with unit tests using JUnit 5. Submit the solution as a link to the repository
- Use the following annotations in your tests: @Test, @Tag, @ParameterizedTest, @DisplayName
- Use assertions such as assertEquals or other relevant assertions
- Set up Allure reports and include a screenshot of the passed tests
- Bonus: Calculate the test coverage percentage using Test Coverage

## Calculation algorithm
The calculation function receives the following inputs: the distance, package size, fragility information, and the current load of the delivery service. The output should be the calculated delivery fee.

#### Distance to the destination:

- Over 30 km: +300$ for the delivery fee
- Up to 30 km (inclusive): +200$ for the delivery fee
- Up to 10 km (inclusive): +100$ for the delivery fee
- Up to 2 km (inclusive): +50$ for the delivery fee

#### Package size:

- Large package: +200$ for the delivery fee
- Small package: +100$ for the delivery fee

#### Fragility of the package:

- If the package is fragile: +300$ for the delivery fee
- Fragile packages cannot be delivered over 30 km

#### Delivery service load:

- Very high load: multiplier of 1.6
- High load: multiplier of 1.4
- Increased load: multiplier of 1.2
- In all other cases, the multiplier is 1

#### Minimum delivery fee:

The minimum delivery fee is 400$. If the calculated delivery fee is less than this amount, the minimum fee should be applied.
