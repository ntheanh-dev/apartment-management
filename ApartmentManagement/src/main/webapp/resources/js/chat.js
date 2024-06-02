$(document).ready(function () {

    //Test render message
    renderMessage("Sau tiếng còi mãn cuộc, Mourinho đã xuống sân nói chuyện ngắn, ôm và an ủi HLV Edin Terzic của Dortmund. Khi được hỏi về cuộc nói chuyện này, HLV Bồ Đào Nha tiết lộ: \"Terzic nói thất bại này rất khó nuốt, và tôi nói rằng thời gian sẽ không giúp ích được gì bởi thất bại sẽ khó khăn cho phần còn lại của sự nghiệp cũng như cuộc đời. Nhưng ông ấy phải rất tự hào vì đã làm công việc tuyệt vời. Ông ấy mang lại sự tổ chức, tự tin, tinh thần và không thể làm gì hơn nữa. Dortmund đã làm tốt mọi thứ ngoại trừ việc ghi bàn\".", 'sent', "Thế Em")
    renderMessage("Sau tiếng còi mãn cuộc, Mourinho đã xuống sân nói chuyện ngắn, ôm và an ủi HLV Edin Terzic của Dortmund. Khi được hỏi về cuộc nói chuyện này, HLV Bồ Đào Nha tiết lộ: \"Terzic nói thất bại này rất khó nuốt, và tôi nói rằng thời gian sẽ không giúp ích được gì bởi thất bại sẽ khó khăn cho phần còn lại của sự nghiệp cũng như cuộc đời. Nhưng ông ấy phải rất tự hào vì đã làm công việc tuyệt vời. Ông ấy mang lại sự tổ chức, tự tin, tinh thần và không thể làm gì hơn nữa. Dortmund đã làm tốt mọi thứ ngoại trừ việc ghi bàn\".", 'sent', "Thế Em")


    //-------------Gửi tin nhan----------------
    $('#sendMess').click(() => {
        var message = $("#messInput").val()
        if(message) {
            $("#messInput").val('')
            renderMessage(message, 'receiver', "Thế Anh")
        }
    })
})

function renderMessage(message, type, sender) {
    // Create the message div
    var messageDiv = $('<div></div>').addClass('border-b border-gray-200 mx-auto p-2.5 overflow-hidden h-auto w-auto');

    // Create the sender span and message body elements
    var senderSpan = $('<span></span>');
    var messageBody = $('<p></p>');

    // Add classes based on the type of message
    if (type === 'sent') {
        senderSpan.addClass('font-bold text-[#00aff0]');
        messageBody.addClass('mt-2 mb-4 font-normal text-left float-left p-2.5 w-3/5 max-w-3/5 border border-[#d9f4ff] rounded-br-lg rounded-bl-lg bg-[#d9f4ff] text-black');
    } else {
        senderSpan.addClass('text-[#00aff0] font-bold float-right');
        messageBody.addClass('mt-2 mb-4 font-normal float-right p-2.5 text-left w-3/5 max-w-3/5 border border-[#d9f4ff] rounded-tl-lg rounded-tr-lg rounded-bl-lg bg-[#d9f4ff] text-black');
    }

    // Set the sender's name
    senderSpan.text(sender);
    var br = $('<br>');

    // Handling links in the message
    if (message.indexOf('https://') >= 0) {
        var text = '';
        var link = '';
        var i;

        // Extract text before the link
        for (i = 0; i < message.indexOf('https://'); i++) {
            text += message[i];
        }
        if (text) {
            messageBody.append(document.createTextNode(text));
        }

        // Extract the link
        for (i = message.indexOf('https://'); i < message.length; i++) {
            link += message[i];
            if (message[i + 1] === ' ') {
                break;
            }
        }
        var linkText = document.createTextNode(link);
        var anchor = $('<a></a>')
            .attr('href', link)
            .css({ color: 'blue', textDecoration: 'underline' })
            .attr('target', '_blank')
            .append(linkText);

        messageBody.append(anchor);

        // Extract text after the link
        text = '';
        for (++i; i < message.length; i++) {
            text += message[i];
        }
        if (text) {
            messageBody.append(document.createTextNode(text));
        }
    } else {
        // Handling normal text
        messageBody.text(message);
    }

    // Append elements to the message div
    messageDiv.append(senderSpan);
    messageDiv.append(br);
    messageDiv.append(messageBody);

    // Append the message div to the messagesDiv and scroll to the bottom
    $('#messagesDiv').append(messageDiv);
    $('#messagesDiv').scrollTop($('#messagesDiv')[0].scrollHeight);
}