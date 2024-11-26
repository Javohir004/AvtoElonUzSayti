package uz.jvh.avtoelonuzsayti.domain.enums;

public enum TransactionStatus {
    INITIATED,       // Tranzaksiya boshlangan
    SUCCESS,         // Tranzaksiya muvaffaqiyatli yakunlangan
    FAILED,          // Tranzaksiya muvaffaqiyatsiz bo'lgan
    CANCELLED        // Tranzaksiya bekor qilingan
}
