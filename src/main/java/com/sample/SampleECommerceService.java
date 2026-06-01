package com.sample;

import java.util.*;
import java.io.*;

/**
 * SampleECommerceService
 *
 * A SAMPLE Java class with INTENTIONAL inefficiencies for demonstrating
 * the Java Energy Analyzer.
 *
 * This class contains:
 *   - O(n²) nested loops → detectable by analyzer
 *   - Recursive methods with branching → exponential risk
 *   - String concatenation in loops → anti-pattern
 *   - Excessive object creation → GC pressure
 *   - High cyclomatic complexity methods
 *   - Well-written methods (for contrast)
 *
 * EXPECTED ANALYSIS RESULTS:
 *   processAllOrders()    → O(n²), EEI ~45, MEDIUM tier, Hotspot
 *   computeDiscount()     → O(2^n), EEI ~5, CRITICAL tier, Hotspot
 *   buildOrderReport()    → O(n), EEI ~55, anti-pattern: String concat
 *   findDuplicates()      → O(n²), EEI ~40, MEDIUM tier
 *   getOrderById()        → O(1), EEI ~95, LOW tier, efficient
 *   calculateTotal()      → O(n), EEI ~80, LOW tier, well-written
 */
public class SampleECommerceService {

    private final Map<String, Order> orderCache = new HashMap<>();

    // ═══════════════════════════════════════════════════════════════
    // INEFFICIENT METHODS (for demonstration)
    // ═══════════════════════════════════════════════════════════════

    /**
     * ANTI-PATTERN: O(n²) — Nested loops
     *
     * Compares every order against every other order.
     * For 1000 orders: 1,000,000 comparisons.
     * EEI expected: ~45 (MEDIUM tier, Hotspot)
     *
     * FIX: Use a HashMap for O(n) lookup.
     */
    public List<String> processAllOrders(List<Order> orders) {
        List<String> duplicates = new ArrayList<>();

        // Outer loop: O(n)
        for (int i = 0; i < orders.size(); i++) {
            // Inner loop: O(n) → total: O(n²)
            for (int j = i + 1; j < orders.size(); j++) {
                if (orders.get(i).getCustomerId().equals(orders.get(j).getCustomerId())) {
                    String result = "DUPLICATE: " + orders.get(i).getId();
                    duplicates.add(result);
                }
            }
        }
        return duplicates;
    }

    /**
     * ANTI-PATTERN: String concatenation in a loop
     *
     * Each '+' in the loop creates a new String object.
     * For n items: O(n²) character copies.
     * EEI expected: ~52 (MEDIUM tier)
     *
     * FIX: Use StringBuilder.append() inside the loop.
     */
    public String buildOrderReport(List<Order> orders) {
        String report = "=== ORDER REPORT ===\n";  // String field

        // ANTI-PATTERN: String concat inside loop
        for (Order order : orders) {
            report = report + "Order: " + order.getId()
                   + " | Customer: " + order.getCustomerId()
                   + " | Total: " + order.getTotal() + "\n";
        }

        report = report + "Total orders: " + orders.size();
        return report;
    }

    /**
     * ANTI-PATTERN: Exponential recursion (no memoization)
     *
     * Calls itself twice → O(2^n) time complexity.
     * For n=30: ~1 billion calls.
     * EEI expected: ~5 (CRITICAL tier, Hotspot)
     *
     * FIX: Use memoization or iterative DP.
     */
    public long computeDiscount(int loyaltyLevel) {
        if (loyaltyLevel <= 1) return loyaltyLevel;
        // Two recursive calls → exponential branching
        return computeDiscount(loyaltyLevel - 1)
             + computeDiscount(loyaltyLevel - 2);
    }

    /**
     * ANTI-PATTERN: Triple nested loops → O(n³)
     *
     * Finds all 3-way product combinations.
     * For n=100 products: 1,000,000 iterations.
     * EEI expected: ~18 (CRITICAL tier, Hotspot)
     */
    public List<String> findProductCombinations(List<String> products) {
        List<String> combinations = new ArrayList<>();
        // Triple nesting = O(n³)
        for (int i = 0; i < products.size(); i++) {
            for (int j = i + 1; j < products.size(); j++) {
                for (int k = j + 1; k < products.size(); k++) {
                    combinations.add(products.get(i) + "+" + products.get(j) + "+" + products.get(k));
                }
            }
        }
        return combinations;
    }

    /**
     * ANTI-PATTERN: High cyclomatic complexity + excessive object creation
     *
     * 12+ decision points, creates many objects per call.
     * EEI expected: ~40 (HIGH tier)
     */
    public Order processOrderRequest(String customerId, String productId,
                                     int quantity, String couponCode,
                                     boolean isPremium, String region) {
        // Many decision points → high cyclomatic complexity
        if (customerId == null || customerId.isEmpty()) {
            throw new IllegalArgumentException("Invalid customer");
        }

        if (productId == null) {
            return new Order("ERROR", customerId, 0.0);
        }

        double price = 100.0;
        double discount = 0.0;

        if (isPremium && quantity > 10) {
            discount = 0.20;
        } else if (isPremium && quantity > 5) {
            discount = 0.15;
        } else if (isPremium) {
            discount = 0.10;
        } else if (quantity > 20) {
            discount = 0.05;
        }

        if (couponCode != null && couponCode.equals("SAVE10")) {
            discount += 0.10;
        } else if (couponCode != null && couponCode.equals("SAVE20")) {
            discount += 0.20;
        } else if (couponCode != null && couponCode.startsWith("BULK")) {
            discount += 0.25;
        }

        if (region != null && region.equals("EU")) {
            price *= 1.20;
        } else if (region != null && region.equals("UK")) {
            price *= 1.15;
        }

        // Excessive object creation
        Order order = new Order(UUID.randomUUID().toString(), customerId, price * quantity * (1 - discount));
        List<String> tags = new ArrayList<>();
        tags.add(isPremium ? "PREMIUM" : "STANDARD");
        tags.add(region != null ? region : "US");
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("coupon", couponCode);
        metadata.put("discount", discount);
        metadata.put("tags", tags);

        return order;
    }

    /**
     * ANTI-PATTERN: Duplicate detection with O(n²) nested loops
     * EEI expected: ~42 (MEDIUM-HIGH tier)
     *
     * FIX: Use HashSet for O(n) detection.
     */
    public List<String> findDuplicates(List<String> items) {
        List<String> duplicates = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            for (int j = i + 1; j < items.size(); j++) {
                if (items.get(i).equals(items.get(j)) && !duplicates.contains(items.get(i))) {
                    duplicates.add(items.get(i));
                }
            }
        }
        return duplicates;
    }

    // ═══════════════════════════════════════════════════════════════
    // EFFICIENT METHODS (for contrast)
    // ═══════════════════════════════════════════════════════════════

    /**
     * EFFICIENT: O(1) — HashMap lookup
     * EEI expected: ~95 (LOW tier)
     */
    public Order getOrderById(String orderId) {
        return orderCache.get(orderId);
    }

    /**
     * EFFICIENT: O(n) — Single linear pass, no anti-patterns
     * EEI expected: ~82 (LOW tier)
     */
    public double calculateTotal(List<Order> orders) {
        double total = 0.0;
        for (Order order : orders) {
            total += order.getTotal();
        }
        return total;
    }

    /**
     * EFFICIENT: O(n) — Uses StringBuilder, single pass
     * EEI expected: ~78 (LOW tier)
     */
    public String buildReportEfficient(List<Order> orders) {
        StringBuilder sb = new StringBuilder(orders.size() * 64);
        sb.append("=== ORDER REPORT ===\n");
        for (Order order : orders) {
            sb.append("Order: ").append(order.getId())
              .append(" | Total: ").append(order.getTotal()).append('\n');
        }
        sb.append("Total orders: ").append(orders.size());
        return sb.toString();
    }

    /**
     * EFFICIENT: O(1) — Simple getter
     * EEI expected: ~98 (LOW tier)
     */
    public int getCacheSize() {
        return orderCache.size();
    }

    // ── Inner class ───────────────────────────────────────────────
    public static class Order {
        private final String id;
        private final String customerId;
        private final double total;

        public Order(String id, String customerId, double total) {
            this.id = id;
            this.customerId = customerId;
            this.total = total;
        }

        public String getId() { return id; }
        public String getCustomerId() { return customerId; }
        public double getTotal() { return total; }
    }
}
