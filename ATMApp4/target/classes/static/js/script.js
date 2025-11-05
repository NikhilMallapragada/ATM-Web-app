
	emailjs.init("k2JgiyojRGrAwI3Il");

	let generatedOtp = null;

	document.getElementById("sendOtpBtn").addEventListener("click", function() {
	    const email = document.getElementById("email").value;
	    generatedOtp = Math.floor(100000 + Math.random() * 900000);
	    
	    const params = {
	        to_email: email,
	        otp: generatedOtp
	    };

	    emailjs.send("YOUR_SERVICE_ID", "YOUR_TEMPLATE_ID", params)
	        .then(() => {
	            alert("OTP sent to your email!");
	            document.getElementById("otpSection").style.display = "block";
	        })
	        .catch((error) => {
	            console.error("Failed to send OTP:", error);
	        });
	});

	document.getElementById("verifyOtpBtn").addEventListener("click", function() {
	    const enteredOtp = document.getElementById("otpInput").value;

	    if (enteredOtp == generatedOtp) {
	        alert("Login successful!");
	        window.location.href = "/dashboard";
	    } else {
	        alert("Incorrect OTP. Please try again.");
	    }
	});
