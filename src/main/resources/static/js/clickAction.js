// Функция для отправки данных на сервер
function sendData() {
    // Получаем значение из текстового поля
    const textValue = document.getElementById('name').value;

    // Создаём объект данных для отправки
    const data = {
        messageType: "CLICK",
        name: textValue
    };

    // Преобразуем объект в JSON строку
    const jsonData = JSON.stringify(data);

    // Отправляем POST запрос
    fetch('/firstClick/click', { // Замените на ваш URL
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: jsonData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            // Здесь можно обработать ответ сервера
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

// Добавляем обработчик события на кнопку
document.getElementById('clickButton').addEventListener('click', sendData);