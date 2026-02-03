# Türkiye Günlük Akaryakıt Fiyatları (Android)

Bu örnek Android uygulaması, Türkiye'deki günlük akaryakıt fiyatlarını ücretsiz bir API uç noktasından çekmek üzere tasarlanmıştır. Varsayılan olarak `BuildConfig.FUEL_API_URL` içinde tanımlı URL'e istek atar. İstek başarısız olursa örnek veri olarak `app/src/main/assets/sample_prices.json` dosyasını kullanır.

## Özellikler
- Ücretsiz API uç noktası üzerinden fiyat çekme (URL yapılandırılabilir).
- API hatalarında örnek veri ile devam etme.
- Basit ve okunaklı liste arayüzü (Jetpack Compose).

## Konfigürasyon
`app/build.gradle.kts` içindeki `FUEL_API_URL` değerini kullanmak istediğiniz ücretsiz API adresiyle değiştirin.

## Çalıştırma
1. Android Studio ile projeyi açın.
2. Gerekli Gradle bağımlılıklarının indirildiğinden emin olun.
3. Uygulamayı bir emülatör ya da fiziksel cihaz üzerinde çalıştırın.

> Not: Kullanacağınız API'nin CORS veya erişim kısıtları varsa, bu kısıtlar uygulama içinde de geçerli olacaktır. Böyle bir durumda API sağlayıcısının kullanım şartlarına göre bir proxy katmanı eklemeniz gerekebilir.
