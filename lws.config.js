// lws.config.js
// Config file for local-web-server

module.exports = {
    port: 8000,
    rewrite: [
        {
            from: '/api/*',
            to: 'http://localhost:8080/api/$1',
        }
    ],
    directory: 'src/main/webapp'
}
