'use client';
type Booking={
    id: number,
    date: string,
    customerName: string,
    amount: string,
    roomCount: string,
    details: object
}

import { useState } from "react";

export default function BookingPage() {

    const [bookings, setBookings] = useState<Booking[]>([
        {id:1, date:'2023-01-01', customerName:'John Doe', amount:'100', roomCount:'1', details:{}},
        {id:2, date:'2023-01-02', customerName:'Jane Smith', amount:'200', roomCount:'2', details:{}},
        {id:3, date:'2023-01-03', customerName:'Alice Johnson', amount:'300', roomCount:'3', details:{}},
    ])

function handleDelete(id:number){
    setBookings(bookings.filter((booking) => booking.id !== id));
}

    return(
        <div className="h-screen bg-gray-100 flex flex-col items-center justify-start p-8">
            
            <h2 className="text-2xl font-bold mb-6">Bookings</h2>

            <div className="w-full max-w-4xl bg-white rounded-lg shadow overflow-hidden">
                <table className="w-full text-left border-collapse">
                    <thead className="bg-blue-600 text-white">
                        <tr>
                            <th className="px-4 py-3">#</th>
                            <th className="px-4 py-3">Date</th>
                            <th className="px-4 py-3">Customer Name</th>
                            <th className="px-4 py-3">Amount</th>
                            <th className="px-4 py-3">Room Count</th>
                            <th className="px-4 py-3">Tools</th>
                        </tr>
                    </thead>

                    <tbody>
                        {bookings.map((booking,index) => (
                            <tr key={booking.id} className="border-b hover:bg-gray-50">
                                <td className="px-4 py-3">{index+1}</td>
                                <td className="px-4 py-3">{booking.date}</td>
                                <td className="px-4 py-3">{booking.customerName}</td>
                                <td className="px-4 py-3">{booking.amount}</td>
                                <td className="px-4 py-3">{booking.roomCount}</td>
                                <td className="px-4 py-3">
                                    <button onClick={()=>handleDelete(booking.id)}
                                        className="px-3 py-1 text-sm bg-red-500 text-white rounded hover:bg-red-600">
                                        Delete
                                    </button>

                                    <button
                                        className="px-3 py-1 text-sm bg-green-500 text-white rounded hover:bg-green-600">
                                        Manage
                                    </button>
                                </td>
                            </tr>
                        ))}
                        {bookings.length === 0 && (
                            <tr>
                                <td colSpan={5} className="text-center py-4 text-gray-500">
                                    No Result Found!
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>

        </div>
    );
}