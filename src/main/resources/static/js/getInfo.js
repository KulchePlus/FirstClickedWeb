document.addEventListener('DOMContentLoaded', () => {
    const dynamicTextElement = document.getElementById('dynamic-text');

    function fetchTextFromServer() {
        fetch('/firstClick/getInfo') // Замените на ваш URL
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok ' + response.statusText);
                }
                return response.text()
            })
            .then(text => {
                dynamicTextElement.textContent = text;
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
                dynamicTextElement.textContent = 'Failed to load text';
            });
    }

    // Выполняем запрос сразу после загрузки страницы
    fetchTextFromServer();

    // Устанавливаем интервал для выполнения запроса каждую секунду
    setInterval(fetchTextFromServer, 1000);
});