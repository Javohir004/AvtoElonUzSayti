package uz.jvh.avtoelonuzsayti.domain.enums;

public enum PaymentStatus {
    PENDING,         // To'lov amalga oshirilmagan
    COMPLETED,       // To'lov muvaffaqiyatli bajarilgan
    FAILED,          // To'lov muvaffaqiyatsiz tugagan
    REFUNDED         // Pul qaytarilgan
}
