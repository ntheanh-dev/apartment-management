import {
    getFirestore, updateDoc, arrayUnion, arrayRemove, doc, setDoc,
    onSnapshot, collection, query, where, orderBy, limit, serverTimestamp
}
    from "https://www.gstatic.com/firebasejs/10.12.2/firebase-firestore.js";


$(document).ready(async function () {
    let db = getFirestore();
    const username = localStorage.getItem('username')
    try {
        const user = await fetUsersByName(username)
        if(user.length ===0 || user[0].username !== username) {
            throw new Error("Không tìm thấy admin")
        }
        const rooms = await fetchCurrentUserConversation(username);
        if(rooms.length == 0) {
            throw new Error("Không tìm thấy dữ liệu tin nhắn")
        }
        let currentRoomId = rooms[0].id;

        if(currentRoomId) {
            //------------Theo dõi du lieu thay đổi
            const q = query(collection(db, "messages"), where('roomId', '==', currentRoomId), orderBy('createAt'), limit(30));
            onSnapshot(q, (querySnapshot) => {
                querySnapshot.docChanges().forEach((change) => {
                    if (change.type === "added") {
                        const {text, senderId} = change.doc.data()
                        if (senderId === username) {
                            renderMessage(text, "", senderId)
                        } else {
                            renderMessage(text, "sent", senderId)
                        }
                    }
                });
            });
            //-------------Gửi tin nhan----------------
            $('#sendMess').click(() => {
                var message = $("#messInput").val()
                if (message) {
                    addDocument("messages", {
                        text: message,
                        senderId: username,
                        roomId: currentRoomId
                    })
                    $("#messInput").val('')
                }
            })

        }
    } catch (ex) {
        Swal.fire({
            title: ex.message,
            text: 'Nguyên nhân có thể do internet hoặc máy chủ không phản hồi. Trở về trang chủ?',
            icon: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
        }).then(() => {
            window.history.back()
        })
    }

    // if(rooms.length === 0) {
    //     // Swal.fire({
    //     //     title: 'Không thể thực hiện chức năng này.',
    //     //     text: 'Nguyên nhân có thể do internet hoặc máy chủ không phản hồi.',
    //     //     icon: 'warning',
    //     //     confirmButtonColor: '#3085d6',
    //     //     confirmButtonText: 'Ok',
    //     // }).then(() => {
    //     //     window.history.back()
    //     // })
    // }

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
            .css({color: 'blue', textDecoration: 'underline'})
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

//--------------------Firebase------------------------
const fetUsersByName = (username) => {
    const condition = {
        field: 'username',
        operator: '==',
        value: username
    }
    return fetchFirebaseData('users', condition)
}

function fetchCurrentUserConversation(username) {
    const condition = {
        field: 'members',
        operator: 'array-contains',
        value: username
    }
    return fetchFirebaseData('rooms', condition)
}
const addDocument = (collectionName, data) => {
    let db = getFirestore();
    (async () => {
        if (data.id)
            await setDoc(doc(db, collectionName, data.id), {
                ...data,
                createAt: serverTimestamp()
            })
        else {
            const newData = doc(collection(db, collectionName));
            await setDoc(newData, {
                ...data,
                createAt: serverTimestamp(),
                id: newData.id
            });
        }
    })();
}
// condition =  {
//     field: 'abc',
//     operator: '==', 'in,','array-contains'...,
//     value: 'dvas'
// }
function fetchFirebaseData(dbName, condition) {
    return new Promise((resolve, reject) => {
        const db = getFirestore();
        const q = query(collection(db, dbName),
            where(condition.field, condition.operator, condition.value)
        )
        onSnapshot(q, (snapshot) => {
            const result = []
            snapshot.docs.forEach((doc) => {
                result.push({...doc.data()})
            })
            resolve(result)
        })
    })
}


////////////////Localstorage////////////
function getCurrentUserData() {
    return localStorage.getItem("username");
}

function setSelectedChat(id) {
    localStorage.setItem("selectedChatId", JSON.stringify(id));
}