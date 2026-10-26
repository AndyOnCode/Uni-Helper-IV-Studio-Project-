package ec.edu.espe.ivstudio.unihelper.model;
import java.util.List;
/**
 *
 * @author Tomas Tapia, IV Studio, @ESPE
 */
public class BudgetService {

    private static final long ACADEMIC_SURCHARGE_CENTS = 1000L;
    private static final long EAT_OUT_LUNCH_CENTS = 350L;
    private static final long PERSONAL_CAR_COMMUTE_CENTS = 400L;
    private static final long PUBLIC_BUS_COMMUTE_CENTS = 50L;

    public long calculateDailyBudget(
            SpendingTier tier,
            boolean eatAtCampus,
            boolean usesPersonalCar,
            List<String> detectedKeywords
    ) {
        long dailyTotal = tier.getBaseAllowanceCents();

        if (eatAtCampus) {
            dailyTotal += EAT_OUT_LUNCH_CENTS;
        }

        dailyTotal += usesPersonalCar ? PERSONAL_CAR_COMMUTE_CENTS : PUBLIC_BUS_COMMUTE_CENTS;

        boolean hasSpecialActivity = detectedKeywords.stream()
                .map(String::toLowerCase)
                .anyMatch(kw -> kw.contains("exam") || kw.contains("laboratory") || kw.contains("lab"));

        if (hasSpecialActivity) {
            dailyTotal += ACADEMIC_SURCHARGE_CENTS;
        }

        return dailyTotal;
    }

    public static String formatCentsToCurrency(long cents) {
        return String.format("$%.2f", cents / 100.0);
    }
}

