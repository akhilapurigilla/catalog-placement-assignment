# catalog-placement-assignment

### Problem
We are given a JSON file containing encoded polynomial roots. Each root provides:
- x → the key in JSON
- y → encoded value in some base

We must:
1. Parse and decode values.
2. Perform Lagrange interpolation.
3. Extract the constant term c of the polynomial.

---

### Steps
1. Parse JSON input from test_case.json.
2. Convert encoded values to base-10 using BigInteger.
3. Apply Lagrange Interpolation using first k points.
4. Evaluate polynomial at x=0 to get c.

---

### How to Run
```bash
javac -cp .:json-20240303.jar LagrangeInterpolation.java
java -cp .:json-20240303.jar LagrangeInterpolation
