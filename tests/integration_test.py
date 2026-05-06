import subprocess
import os

def run_java_engine(prices, quantities, customer_type, discount_code):
    cmd = [
        "java", "-cp", "build/classes/java/main", 
        "pricing.PricingEngine", 
        ",".join(map(str, prices)), 
        ",".join(map(str, quantities)), 
        customer_type, 
        discount_code if discount_code else "null"
    ]
    result = subprocess.run(cmd, capture_output=True, text=True)
    return result.stdout.strip()

def test_integration():
    print("Running integration tests...")
    
    # Test 1: Regular customer, no discount
    output = run_java_engine([10.0, 20.0], [1, 1], "REGULAR", None)
    print(f"Test 1 Output: {output}")
    assert "Final Price: 36.00" in output
    
    # Test 2: VIP customer
    output = run_java_engine([100.0], [1], "VIP", None)
    print(f"Test 2 Output: {output}")
    assert "Final Price: 102.00" in output

    # Test 3: Regular with SAVE10
    output = run_java_engine([50.0], [1], "REGULAR", "SAVE10")
    print(f"Test 3 Output: {output}")
    assert "Final Price: 48.00" in output

    print("All integration tests passed!")

if __name__ == "__main__":
    test_integration()
