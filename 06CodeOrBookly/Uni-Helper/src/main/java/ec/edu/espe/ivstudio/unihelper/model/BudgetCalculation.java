package ec.edu.espe.ivstudio.unihelper.model;

import java.util.List;

public class BudgetCalculation {
    private SpendingTier tier;
    private boolean eatAtCampus;
    private boolean usesPersonalCar;
    private List<String> keywords;
    private long calculatedBudgetCents;

    public BudgetCalculation(SpendingTier tier, boolean eatAtCampus, boolean usesPersonalCar,
            List<String> keywords, long calculatedBudgetCents) {
        this.tier = tier;
        this.eatAtCampus = eatAtCampus;
        this.usesPersonalCar = usesPersonalCar;
        this.keywords = keywords;
        this.calculatedBudgetCents = calculatedBudgetCents;
    }

    public SpendingTier getTier() { return tier; }
    public boolean isEatAtCampus() { return eatAtCampus; }
    public boolean isUsesPersonalCar() { return usesPersonalCar; }
    public List<String> getKeywords() { return keywords; }
    public long getCalculatedBudgetCents() { return calculatedBudgetCents; }
}
