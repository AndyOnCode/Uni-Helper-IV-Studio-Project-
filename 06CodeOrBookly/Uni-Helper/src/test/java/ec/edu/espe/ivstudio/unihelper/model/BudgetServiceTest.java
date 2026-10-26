package ec.edu.espe.ivstudio.unihelper.model;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BudgetServiceTest {

    private final BudgetService service = new BudgetService();

    @Test
    @DisplayName("[Low] Calculates save tier with public bus and no extras")
    void calculatesSaveTierWithPublicBusAndNoExtras() {
        long result = service.calculateDailyBudget(SpendingTier.SAVE, false, false, List.of());

        assertEquals(550L, result);
    }

    @Test
    @DisplayName("[Low] Adds lunch and personal car costs")
    void addsLunchAndPersonalCarCosts() {
        long result = service.calculateDailyBudget(SpendingTier.RECOMMENDED, true, true, List.of());

        assertEquals(1_550L, result);
    }

    @Test
    @DisplayName("[Medium] Adds academic surcharge for exam keyword")
    void addsAcademicSurchargeForExamKeyword() {
        long result = service.calculateDailyBudget(
                SpendingTier.SOCIAL,
                false,
                false,
                List.of("midterm EXAM")
        );

        assertEquals(2_450L, result);
    }

    @Test
    @DisplayName("[Medium] Adds academic surcharge for laboratory or lab keyword")
    void addsAcademicSurchargeForLaboratoryOrLabKeyword() {
        assertEquals(
                1_550L,
                service.calculateDailyBudget(SpendingTier.SAVE, false, false, List.of("Laboratory"))
        );
        assertEquals(
                1_550L,
                service.calculateDailyBudget(SpendingTier.SAVE, false, false, List.of("lab session"))
        );
    }

    @Test
    @DisplayName("[Low] Does not add academic surcharge for ordinary keywords")
    void doesNotAddAcademicSurchargeForOrdinaryKeywords() {
        long result = service.calculateDailyBudget(
                SpendingTier.RECOMMENDED,
                false,
                false,
                List.of("lecture", "homework")
        );

        assertEquals(850L, result);
    }

    @Test
    @DisplayName("[Low] Formats cents as currency")
    void formatsCentsAsCurrency() {
        assertEquals("$12.34", BudgetService.formatCentsToCurrency(1_234L));
        assertEquals("$0.00", BudgetService.formatCentsToCurrency(0L));
    }

    @Test
    @DisplayName("[Low] Uses the social tier base allowance")
    void usesSocialTierBaseAllowance() {
        long result = service.calculateDailyBudget(SpendingTier.SOCIAL, false, false, List.of());

        assertEquals(1_450L, result);
    }

    @Test
    @DisplayName("[Medium] Adds the academic surcharge only once for multiple matching keywords")
    void addsAcademicSurchargeOnlyOnceForMultipleMatchingKeywords() {
        long result = service.calculateDailyBudget(
                SpendingTier.SAVE,
                false,
                false,
                List.of("exam", "laboratory", "lab")
        );

        assertEquals(1_550L, result);
    }

    @Test
    @DisplayName("[Medium] Detects academic keywords regardless of letter case")
    void detectsAcademicKeywordsRegardlessOfCase() {
        long result = service.calculateDailyBudget(
                SpendingTier.SAVE,
                false,
                false,
                List.of("FINAL EXAM")
        );

        assertEquals(1_550L, result);
    }

    @Test
    @DisplayName("[Hard] Combines all costs with an academic surcharge")
    void combinesAllCostsWithAcademicSurcharge() {
        long result = service.calculateDailyBudget(
                SpendingTier.SOCIAL,
                true,
                true,
                List.of("laboratory")
        );

        assertEquals(3_150L, result);
    }

    @Test
    @DisplayName("[Hard] Exposes crash when detected keywords are null")
    void throwsWhenDetectedKeywordsAreNull() {
        assertThrows(
                NullPointerException.class,
                () -> service.calculateDailyBudget(SpendingTier.SAVE, false, false, null)
        );
    }

    @Test
    @DisplayName("[Hard] Exposes crash when spending tier is null")
    void throwsWhenSpendingTierIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> service.calculateDailyBudget(null, false, false, List.of())
        );
    }

    @Test
    @DisplayName("[Hard] Exposes crash when a detected keyword is null")
    void throwsWhenDetectedKeywordIsNull() {
        assertThrows(
                NullPointerException.class,
                () -> service.calculateDailyBudget(
                        SpendingTier.SAVE,
                        false,
                        false,
                        java.util.Arrays.asList((String) null)
                )
        );
    }
}
