package ValidateLibrary;

public class LibraryValidation {

    // =========================
    // Validate ID
    // =========================

    public static boolean validateId(String id) {

        if (id == null || id.trim().isEmpty()) {

            System.out.println(
                    "ID cannot be blank."
            );

            return false;
        }

        return true;
    }


    // =========================
    // Validate Text
    // =========================

    public static boolean validateText(String text) {

        if (text == null || text.trim().isEmpty()) {

            System.out.println(
                    "Value cannot be empty."
            );

            return false;
        }

        return true;
    }


    // =========================
    // Validate Quantity
    // =========================

    public static boolean validateQuantity(int quantity) {

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than 0."
            );

            return false;
        }

        return true;
    }
}