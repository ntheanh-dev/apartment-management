import {
    getFirestore, doc, setDoc,getDocs,
    onSnapshot, collection, query, where, orderBy, serverTimestamp
}
    from "https://www.gstatic.com/firebasejs/10.12.2/firebase-firestore.js";


$(document).ready(async function () {
    const username = localStorage.getItem('username')
    if(!username || username === "anonymousUser") {
        window.history.back()
    }
    let db = getFirestore();
    let currentRoomId = null;
    try {
        const collection_ref = collection(db, 'users');
        const q = query(collection_ref, where('username', '==', username));
        const docRefs = await getDocs(q);
        let res = [];
        docRefs.forEach((doc) => res.push({...doc.data()}));
        if (res.length === 0) {
            throw new Error("Không tìm thấy admin")
        } else {
            res = [];
            const collection_ref = collection(db, 'rooms');
            const q = query(collection_ref, where('members', 'array-contains', username));
            const docRefs = await getDocs(q);
            docRefs.forEach((doc) => res.push({...doc.data()}));
            if (res.length === 0) {
                throw new Error('Không tìm thấy dữ liệu');
            } else {
                currentRoomId = res[0].id
                const q = query(
                    collection(db, 'messages'),
                    where('roomId', '==', currentRoomId),
                    orderBy('createAt'),
                );
                onSnapshot(q, (querySnapshot) => {
                    querySnapshot.docChanges().forEach((change) => {
                        if (change.type === 'added') {
                            const {text, senderUsername} = change.doc.data()
                            if (senderUsername === username) {
                                renderMessage(text, "", senderUsername)
                            } else {
                                renderMessage(text, "sent", senderUsername)
                            }
                        }
                    });
                });
            }
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

    //-------------Gửi tin nhan----------------
    $('#sendMess').click(() => {
        var message = $("#messInput").val()
        if (message) {
            addDocument("messages", {
                text: message,
                senderUsername: username,
                roomId: currentRoomId
            })
            $("#messInput").val('')
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
        messageBody.addClass('mt-2 mb-4 font-normal text-left float-left p-2.5 inline-block max-w-[60vw] break-words border border-[#d9f4ff] rounded-br-lg rounded-bl-lg bg-[#d9f4ff] text-black');
    } else {
        senderSpan.addClass('text-[#00aff0] font-bold float-right');
        messageBody.addClass('mt-2 mb-4 font-normal float-right p-2.5 text-left inline-block max-w-[60vw] break-words border border-[#d9f4ff] rounded-tl-lg rounded-tr-lg rounded-bl-lg bg-[#d9f4ff] text-black');
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

////////////////Localstorage////////////
function getCurrentUserData() {
    return localStorage.getItem("username");
}

function setSelectedChat(id) {
    localStorage.setItem("selectedChatId", JSON.stringify(id));
}